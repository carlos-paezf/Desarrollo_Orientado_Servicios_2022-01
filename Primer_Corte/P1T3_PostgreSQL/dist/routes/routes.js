"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const faculty_controller_1 = __importDefault(require("../controllers/faculty.controller"));
const professor_controller_1 = __importDefault(require("../controllers/professor.controller"));
class Routes {
    constructor() {
        /**
         * This is a function that is called when the class is instantiated.
         */
        this.config = () => {
            this.routes.get('/faculties', faculty_controller_1.default.getFaculties);
            this.routes.get('/professors', professor_controller_1.default.getProfessors);
        };
        this.routes = (0, express_1.Router)();
        this.config();
    }
}
const routes = new Routes();
exports.default = routes.routes;
