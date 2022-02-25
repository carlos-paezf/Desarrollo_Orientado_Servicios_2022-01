"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const professor_dao_1 = __importDefault(require("../daos/professor.dao"));
const professor_sql_1 = require("../repositories/professor.sql");
class ProfessorController extends professor_dao_1.default {
    constructor() {
        super(...arguments);
        /* This is a function that will be called when the user requests the `/professors` endpoint. */
        this.getProfessors = (req, res) => {
            professor_dao_1.default.getProfessors(professor_sql_1.SQL_PROFESSOR.ALL, [], res);
        };
        this.getTypeProfessorsAndFaculty = (req, res) => {
            professor_dao_1.default.getTypeAndFaculty(professor_sql_1.SQL_PROFESSOR.TYPE_AND_FACULTY, [], res);
        };
    }
}
const professorController = new ProfessorController();
exports.default = professorController;
