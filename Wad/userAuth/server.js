//npm init
//npm i express bcrypt

const express = require('express')
const app = express()

const bcrypt = require('bcrypt') //for encryption

app.use(express.json()) //allows app to accept json

let users = []

//to get user data
app.get('/users', (req, res) => {
  res.send(users)
})


//to add user data....and encypt when adding
app.post('/users', async (req, res) => {
  try {
    const hashedPassword = await bcrypt.hash(req.body.password, 10) // 10 is the hashing parameter
    const user = { name: req.body.name, password: hashedPassword }
    users.push(user)
    res.send("user data inserted ...")
    res.status(201).send()
  } catch {
    res.status(500).send()
  }
})

// to check and authenticate user data
app.post('/users/login', async (req, res) => {
  const user = users.find(user => user.name === req.body.name) // first see if user is available or not
  if (user == null) {
    return res.status(400).send('Cannot find user')
  }
  try {
    if(await bcrypt.compare(req.body.password, user.password)) { // compare() is used to campare hashed password and request-body-password
      res.send('Success')
    } else {
      res.send('Not Allowed')
    }
  } catch {
    res.status(500).send()
  }
})

app.listen(3000,()=>{
  console.log("auth server running ...");
})