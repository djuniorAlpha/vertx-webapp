package br.com.starter.vertxwebapp.controllers;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.thymeleaf.ThymeleafTemplateEngine;

public class HomeController {

  private static ThymeleafTemplateEngine engine = ThymeleafTemplateEngine.create(Vertx.currentContext().owner());

  public static void index(RoutingContext context) {
    engine.render(new JsonObject(), "templates/index.html", template -> {
      if(template.succeeded()) {
        context.response().end(template.result());
      } else {
        context.fail(template.cause());
      }
    });
  }
}
