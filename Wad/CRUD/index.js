// npm init -y
// npm i mongodb express

const express = require('express')
const dbConnect = require('./mongodb')

const app = express()
app.use(express.json()) //allows app to accept json

//GET, takes url , callback(which takes req,res) as args
//find() is used to get data from server
app.get('/',async (req,res)=>{
    let result = await dbConnect() // returns a promise
    result = await result.find().toArray() 
    res.send(result)
})
 
//POST... uses insertOne() to insert data
app.post('/',async(req,res)=>{
    let result = await dbConnect() // returns a promise
    result = await result.insertOne(req.body)
    res.send("data inserted ...")

})

//PUT...to update...updateOne({},{})
//uses params as comparision field and request-body as updation data
app.put('/:name',async(req,res)=>{
    let result = await dbConnect() // returns a promise
    result = await result.updateOne({name: req.params.name},{$set : req.body})
    res.send("data updated ...")
})

//DELETE...same as PUT ... but does not need new value
app.delete('/:name',async(req,res)=>{
    let result = await dbConnect() // returns a promise
    result = await result.deleteOne({name: req.params.name})
    res.send("data deleted ...")
})

app.listen(3000,()=>{
    console.log("server running ...");
})