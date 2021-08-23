package br.com.starter.vertxwebapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starter.vertxwebapp.controllers.HomeController;

public class MainVerticle extends AbstractVerticle {
  private final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    Router router = Router.router(vertx);
    router.get("/home").handler(HomeController::index);
    router.get().handler(StaticHandler.create());

    vertx.createHttpServer().requestHandler(router).listen(8080, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        logger.info("HTTP server started on port {}", http.result().actualPort());
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
