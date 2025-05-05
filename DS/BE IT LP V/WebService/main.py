from fastapi import FastAPI
import random

app = FastAPI()

quotes = [
    "The best way to get started is to quit talking and begin doing.",
    "Don’t let yesterday take up too much of today.",
    "It’s not whether you get knocked down, it’s whether you get up."
]

@app.get("/quote")
def get_quote():
    return {"quote": random.choice(quotes)}
