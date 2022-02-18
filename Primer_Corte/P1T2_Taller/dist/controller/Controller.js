"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const DAO_1 = __importDefault(require("../dao/DAO"));
class Controller extends DAO_1.default {
    constructor() {
        super(...arguments);
        this.getUsers = (req, res) => Controller.getUsersDAO(req, res);
        this.getProducts = (req, res) => Controller.getProductsDAO(req, res);
        this.getServices = (req, res) => Controller.getServicesDAO(req, res);
    }
}
const controller = new Controller();
exports.default = controller;
