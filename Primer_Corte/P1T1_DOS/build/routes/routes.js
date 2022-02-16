"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const controller_1 = __importDefault(require("../controllers/controller"));
class Routes {
    /**
     * The constructor function is used to create the routes for the API
     */
    constructor() {
        this.testRouteAPI = (0, express_1.Router)();
        this.config();
    }
    /**
     * `config()` is a function that takes no parameters and returns nothing.
     * used to configure the application.
     * called automatically when the application is started.
     * a good place to add routes and configure the application.
     * The code above adds a route to the application.
     * The route is a request handler.
     * When the application receives a request to `/api/get-info`, the code calls the `attackMe()`
     * function
     */
    config() {
        this.testRouteAPI.get('/get-info', controller_1.default.attackMe);
    }
}
const routes = new Routes();
exports.default = routes.testRouteAPI;
