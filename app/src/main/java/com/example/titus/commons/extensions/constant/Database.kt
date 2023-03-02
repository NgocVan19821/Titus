package com.example.titus.commons.extensions.constant

class Database {
    companion object {
        //        News
        const val idPost = "idPost"
        const val textPost = "textPost"
        const val mediasPost = "mediasPost"
        const val qualitiesReact = "qualitiesReact"
        const val avatarAuthor = "avatarAuthor"
        const val nameAuthor = "nameAuthor"
        const val dateUpPost = "dateUpPost"
        const val listReact = "listReact"
        const val listComment = "listComment"

        //        Comment
        const val idReact = "idReact"
        const val avatarReact = "avatar"
        const val nameReact = "name"



        //        -------------------------
        //      Path
        val pathPostNew = "Users/${Application.idUser}/News"

        //        -- Path to react
        fun pathReact(pathPost: String): String {
            return "$pathPostNew/$pathPost/listReact"
        }

        //        -- Path to comment
        fun pathComment(pathPost: String): String {
            return "$pathPostNew/$pathPost/listComment"
        }

        //        -- Path to reply
        fun pathReply(pathPost: String, pathComment: String): String {
            return "${pathComment(pathPost)}/listComment/$pathComment"
        }
    }
}