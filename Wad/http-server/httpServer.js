//npm init -y

const http = require("http"); // built in http module 

/**createServer method of http module is used to create a server
 * this runs a callback fun whenever the srever receives a request
 * the callback accepts 2 args, request(obj containing info of request) and response(variable)
 */
const server = http.createServer((req,res)=>{
    // we can provide response based on the request-url

    //for homepage
    if(req.url == '/'){
        res.write("homepage \n hello, visit /about for more info ")
        res.end()
    }

    // for about page
    if(req.url == '/about'){
        res.write("about \n a server created by te21it011")
        res.end()
    }
    //redault response
    res.write("invalid access detected, please go back")
    res.end()
}) 

//we need a port to run the server on
// and an optional callback that will run if things go right
server.listen(3000,()=>{
    console.log("server started")
})