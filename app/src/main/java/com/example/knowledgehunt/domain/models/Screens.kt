package com.example.knowledgehunt.domain.models

sealed class Screens(val title: String, val route: String) {
    object Home : Screens("Home", "home")
    object Articles : Screens("Articles", "articles")
    object AddArticle : Screens("Write Article", "add_article")
    object ArticleDetails : Screens("Article Details", "article_details")
    object MyArticles : Screens("My Articles", "my_articles")
    object MyArticlesDetails : Screens("My Articles Details", "my_articles_details")
    object About : Screens("About", "about")
    object Splash : Screens("Splash", "splash")
    object Login : Screens("Login", "login")
    object Register : Screens("Create Account", "register")
    object EditProfile : Screens("Edit Profile", "edit_profile")
    object Help : Screens("Help", "help")
    object Search : Screens("Search", "search")
    object Questions : Screens("Questions", "questions")
    object QuestionDetails : Screens("Questions Detail", "question_detail")
    object AddQuestion : Screens("Ask Questions", "add_questions")
    object MyQuestions : Screens("My Questions", "my_questions")
    object MyQuestionsDetails : Screens("Edit Your Question", "my_questions_details")
    object MCQ : Screens("MCQ Tests", "mcq")
}