package com.demo.demostramingapp.model

data class ContentItem(
    val content: List<Content>
)

data class Content(
    val name: String,
    val `poster-image`: String
)

data class Page(
    val title: String,
    val `total-content-items`: String,
    val `page-num`: String,
    val `page-size`: String,
    val `content-items`: ContentItem
)

data class JsonResponse(
    val page: Page
)




