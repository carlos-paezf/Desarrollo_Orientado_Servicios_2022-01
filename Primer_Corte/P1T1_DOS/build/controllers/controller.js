"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const dao_1 = __importDefault(require("../daos/dao"));
class Controller extends dao_1.default {
    /**
     * `attackMe` is a function that takes a `Request` and a `Response` as arguments
     * @param {Request} req - Request
     * @param {Response} res - Response - The response object.
     */
    attackMe(req, res) {
        Controller.getInfo(req, res);
    }
}
const controller = new Controller();
exports.default = controller;
