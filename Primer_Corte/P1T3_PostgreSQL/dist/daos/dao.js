"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
var _a;
Object.defineProperty(exports, "__esModule", { value: true });
const colors_1 = require("colors");
const connection_DB_1 = __importDefault(require("../config/connection/connection_DB"));
class DAO {
}
_a = DAO;
/**
 * A function that will be used by the class `FacultyDAO` to get the data from the database.
 */
DAO.getFaculties = (sqlQuery, parameters, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const answer = yield connection_DB_1.default.pool.result(sqlQuery, parameters);
        res.status(200).json({ ok: true, resultsQuery: answer.rows });
    }
    catch (error) {
        console.log((0, colors_1.red)(`Error in FacultyDao: `), error);
        res.status(400).json({ ok: false });
    }
});
/**
 * A function that will be used by the class `ProfessorDAO` to get the data from the database.
 */
DAO.getProfessors = (sqlQuery, parameters, res) => __awaiter(void 0, void 0, void 0, function* () {
    try {
        const answer = yield connection_DB_1.default.pool.result(sqlQuery, parameters);
        res.status(200).json({ ok: true, resultsQuery: answer.rows });
    }
    catch (error) {
        console.log((0, colors_1.red)('Error in ProfessorDAO: '), error);
        res.status(400).json({ ok: false });
    }
});
exports.default = DAO;
