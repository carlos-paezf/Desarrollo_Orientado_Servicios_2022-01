"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const faculty_dao_1 = __importDefault(require("../daos/faculty.dao"));
const faculty_sql_1 = require("../repositories/faculty.sql");
class FacultyController extends faculty_dao_1.default {
    constructor() {
        super(...arguments);
        /**
         * This is a function that will be called when the user requests the `/faculties` endpoint.
         */
        this.getFaculties = (req, res) => {
            faculty_dao_1.default.getFaculties(faculty_sql_1.SQL_FACULTY.ALL, [], res);
        };
    }
}
const facultyController = new FacultyController();
exports.default = facultyController;
