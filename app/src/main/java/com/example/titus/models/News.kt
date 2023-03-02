package com.example.titus.models

//News
data class News(
    var idPost: String? = null,
    var textPost: String? = null,
    var mediasPost: MutableList<LinkImage>? = null,
    var qualitiesReact: Int? = null,
    var avatarAuthor: String? = null,
    var nameAuthor: String? = null,
    var dateUpPost: String? = null
)

data class LinkImage(
    var link: String? = null
)

//Reaction news
data class UserReact(
    var avatar: String? = null,
    var name: String? = null
)

//Comment news
data class UserComment(
    var avatar: String? = null,
    var name: String? = null,
    var content: String? = null,
    var image: String? = null,
)

//Reply comments news
data class UserReply(
    var avatar: String? = null,
    var name: String? = null,
    var content: String? = null,
    var image: String? = null,
)
