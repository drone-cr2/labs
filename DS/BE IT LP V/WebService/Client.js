const cluster = require("cluster");

if (cluster.isMaster) {
    for (let i = 0; i < 4; i++) {
        cluster.fork();
    }
} else {
    setInterval(async () => {
        try {
            const res = await fetch("http://localhost:8000/quote");
            const data = await res.json()
            console.log(`Worker ${process.pid} received: ${data.quote}`);
        } catch (err) {
            console.error(`Worker ${process.pid} error:`, err.message);
        }
    }, 2000);
}
