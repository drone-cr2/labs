//npm init -y
//npm install express --save
//(create public folder and create index.html in it)

const express = require('express')

const app = express();

app.use(express.static('public'))

app.listen(3000,()=>{
    console.log("static website hosted ...");
})