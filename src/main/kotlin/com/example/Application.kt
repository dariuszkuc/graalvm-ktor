package com.example

import com.expediagroup.graphql.server.ktor.GraphQL
import com.expediagroup.graphql.server.ktor.graphQLGetRoute
import com.expediagroup.graphql.server.ktor.graphQLPostRoute
import com.expediagroup.graphql.server.ktor.graphQLSDLRoute
import com.expediagroup.graphql.server.ktor.graphiQLRoute
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        install(GraphQL) {
            schema {
                packages = listOf("com.example")
                queries = listOf(HelloWorldQuery())
            }
        }
        routing {
            graphQLGetRoute()
            graphQLPostRoute()
            graphQLSDLRoute()
        }
    }.start(wait = true)
}
