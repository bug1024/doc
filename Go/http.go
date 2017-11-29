package main

import (
    "fmt"
    "net/http"
    "log"
)

func handleRequest(res http.ResponseWriter, req *http.Request) {
    req.ParseForm()
    fmt.Println(req.Form)
    fmt.Fprintf(res, "Hello World!")
}

func main() {
    http.HandleFunc("/", handleRequest)
    err := http.ListenAndServe(":9999", nil)
    if err != nil {
        log.Fatal("ListenAndServe: ", err)
    }
}


