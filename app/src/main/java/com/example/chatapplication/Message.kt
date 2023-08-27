package com.example.chatapplication

class Message {
    var message: String? = null
    var senderId: String? = null

    constructor(){}

    constructor(message: String?, senderId: String?){
        this.message = message
        this.senderId = senderId
    }

}


//a data class that represents a chat message with attributes: message content and sender's UID.