package com.example

import com.expediagroup.graphql.server.operations.Query
import java.util.UUID

class HelloWorldQuery : Query {

    fun hello(name: String? = null): String = if (name != null) {
        "Hello $name"
    } else {
        "Hello World"
    }

    fun foo(): Foo = Foo(1)
}

class Foo(val id: Int) {

    fun random(): String = UUID.randomUUID().toString()
}