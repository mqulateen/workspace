package com.mqul.mp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class BaseController
{
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String getAllActors()
    {
        return html;
    }

    private static String html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\t<head>\n" +
            "\t\t<title>projectMovie</title>\n" +
            "\n" +
            "\t\t<meta name=\"author\" content=\"your name\" />\n" +
            "\t\t<meta name=\"description\" content=\"\" />\n" +
            "\n" +
            "\t\t<style>\n" +
            "\t\t\tp{ line-height: 1em; }\n" +
            "\t\t\th1, h2, h3, h4{color: orange; font-weight: normal; line-height: 1.1em; margin: 0 0 .5em 0;}\n" +
            "\t\t\th1{ font-size: 1.7em; }\n" +
            "\t\t\th2{ font-size: 1.5em; }\n" +
            "\t\t\ta{color: black; text-decoration: none;}\n" +
            "\t\t\ta:hover, a:active{ text-decoration: underline; }\n" +
            "\t\t\tbody{font-family: arial; font-size: 80%; line-height: 1.2em; width: 100%; margin: 0; background: #eee;}\n" +
            "\t\t\t#page{ margin: 20px; }\n" +
            "\t\t\t#logo{width: 35%; margin-top: 5px; font-family: georgia; display: inline-block;}\n" +
            "\t\t\tnav{width: 60%; display: inline-block; text-align: right; float: right;}\n" +
            "\t\t\tnav ul{}\n" +
            "\t\t\tnav ul li{display: inline-block; height: 62px;}\n" +
            "\t\t\tnav ul li a{padding: 20px; background: orange; color: white;}\n" +
            "\t\t\tnav ul li a:hover{background-color: #ffb424; box-shadow: 0px 1px 1px #666;}\n" +
            "\t\t\tnav ul li a:active{ background-color: #ff8f00; }\n" +
            "\t\t\t#content{margin: 30px 0; background: white; padding: 20px; clear: both;}\n" +
            "\t\t\tfooter{border-bottom: 1px #ccc solid; margin-bottom: 10px;}\n" +
            "\t\t\tfooter p{text-align: right; text-transform: uppercase; font-size: 80%; color: grey;}\n" +
            "\t\t\t#content, ul li a{ box-shadow: 0px 1px 1px #999; }\n" +
            "\t\t</style>\n" +
            "\t</head>\n" +
            "\n" +
            "\t<body>\n" +
            "\t\t<div id=\"page\">\n" +
            "\t\t\t<div id=\"logo\">\n" +
            "\t\t\t\t<h1><a href=\"/\" id=\"logoLink\">projectMovie</a></h1>\n" +
            "\t\t\t</div>\n" +
            "\n" +
            "\t\t\t<nav>\n" +
            "\t\t\t\t<ul>\n" +
            "\t\t\t\t\t<li><a href=\"/pmovie\">Home</a></li>\n" +
            "\t\t\t\t\t<li><a href=\"/pmovie/film\">Films</a></li>\n" +
            "\t\t\t\t\t<li><a href=\"/pmovie/actor\">Actors</a></li>\n" +
            "\t\t\t\t\t<li><a href=\"/pmovie/director\">Directors</a></li>\n" +
            "\t\t\t\t</ul>\t\n" +
            "\t\t\t</nav>\n" +
            "\n" +
            "\t\t\t<div id=\"content\">\n" +
            "\t\t\t\t<h2>pMovie</h2>\n" +
            "\t\t\t\t<p>projectMovie, a simple API that allows users to manipulate Film/Actor/Director data - inspired by IMdB.</p>\n" +
            "\t\t\t\t<p>The main technologies being used here include Spring, JPA (Eclipselink), PostgreSQL, and of course Java 8 - with all its OOP glory.</p>\n" +
            "\t\t\t\t<p>Try out the GET endpoints by pressing the buttons above</p>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t\n" +
            "\t\t\t<footer>\n" +
            "\t\t\t\t<p>Webpage made by <a href=\"https://github.com/mqulateen\" target=\"_blank\">Mohamed Qulateen</a></p>\n" +
            "\t\t\t</footer>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "</html>";
}
