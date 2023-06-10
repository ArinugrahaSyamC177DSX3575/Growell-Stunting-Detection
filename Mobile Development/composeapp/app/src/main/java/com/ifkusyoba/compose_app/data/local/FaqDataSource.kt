package com.ifkusyoba.compose_app.data.local

import com.ifkusyoba.compose_app.R
import com.ifkusyoba.compose_app.data.model.Faq


object FaqDataSource{
    val faq = listOf(
        Faq(1, R.string.faq_question1, R.string.faq_answer1),
        Faq(2, R.string.faq_question2, R.string.faq_answer2),
        Faq(3, R.string.faq_question3, R.string.faq_answer3),
        Faq(4, R.string.faq_question4, R.string.faq_answer4),
        Faq(5, R.string.faq_question5, R.string.faq_answer5),
    )
}