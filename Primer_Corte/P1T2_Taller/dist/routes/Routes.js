"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const Controller_1 = __importDefault(require("../controller/Controller"));
class Routes {
    constructor() {
        this.config = () => {
            this.routes.get('/get-users', Controller_1.default.getUsers);
            this.routes.get('/get-products', Controller_1.default.getProducts);
            this.routes.get('/get-services', Controller_1.default.getServices);
        };
        this.routes = (0, express_1.Router)();
        this.config();
    }
}
const routes = new Routes();
exports.default = routes.routes;
