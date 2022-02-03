package com.example.knowledgehunt.domain.models

sealed class Screens(val title: String, val route: String) {
    object Home : Screens("Home", "home")
    object Articles : Screens("Articles", "articles")
    object AddArticle : Screens("Write Article", "add_article")
    object ArticleDetails : Screens("Article Details", "article_details")
    object About : Screens("About", "about")
    object Splash : Screens("Splash", "splash")
    object Login : Screens("Login", "login")
    object Register : Screens("Create Account", "register")
    object Help : Screens("Help", "help")

}