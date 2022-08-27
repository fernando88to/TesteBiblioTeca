package com.fernando

class IndexController {

    def index() {
        render new Library().someLibraryMethod()
    }
}
