"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const professor_controller_1 = __importDefault(require("../controllers/professor.controller"));
class ProfessorRoutes {
    constructor() {
        /**
         * This is a function that is called when the class is instantiated.
         */
        this.config = () => {
            this.professorRoutes.get('/', professor_controller_1.default.getProfessors);
            this.professorRoutes.get('/type-and-faculty', professor_controller_1.default.getTypeProfessorsAndFaculty);
        };
        this.professorRoutes = (0, express_1.Router)();
        this.config();
    }
}
const professorRoutes = new ProfessorRoutes();
exports.default = professorRoutes.professorRoutes;
