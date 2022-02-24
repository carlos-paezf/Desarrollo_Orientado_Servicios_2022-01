"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const faculty_controller_1 = __importDefault(require("../controllers/faculty.controller"));
class FacultyRoutes {
    constructor() {
        /**
         * This is a function that is called when the class is instantiated.
         */
        this.config = () => {
            this.facultyRoutes.get('/', faculty_controller_1.default.getFaculties);
        };
        this.facultyRoutes = (0, express_1.Router)();
        this.config();
    }
}
const facultyRoutes = new FacultyRoutes();
exports.default = facultyRoutes.facultyRoutes;
