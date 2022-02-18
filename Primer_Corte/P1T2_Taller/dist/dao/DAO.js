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
const colors_1 = __importDefault(require("colors"));
const data_1 = require("../data");
class DAO {
}
_a = DAO;
DAO.getUsersDAO = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    return Promise.resolve(data_1.users)
        .then((response) => {
        console.log(colors_1.default.italic('Respuesta: '), response);
        res.status(200).json({ ok: true, response });
    })
        .catch((error) => {
        res.status(400).json({ ok: false });
        throw new Error(error);
    });
});
DAO.getProductsDAO = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    return Promise.resolve(data_1.products)
        .then((response) => {
        console.log(colors_1.default.italic('Respuesta: '), response);
        res.status(200).json({ ok: true, response });
    })
        .catch((error) => {
        res.status(400).json({ ok: false });
        throw new Error(error);
    });
});
DAO.getServicesDAO = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    return Promise.resolve(data_1.services)
        .then((response) => {
        console.log(colors_1.default.italic('Respuesta: '), response);
        res.status(200).json({ ok: true, response });
    })
        .catch((error) => {
        res.status(400).json({ ok: false });
        throw new Error(error);
    });
});
exports.default = DAO;
