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
Object.defineProperty(exports, "__esModule", { value: true });
class DAO {
    /**
     * It returns a JSON object with the professors and course name.
     * @param {Request} req - The request object.
     * @param {Response} res - Response
     * @returns The response is a JSON object with the following structure:
     * ```
     * {
     *     ok: true,
     *     response: {
     *         professors: ['Carlos Andrés', 'Harvey'],
     *         course: 'DOS'
     *     }
     * }
     * ```
     */
    static getInfo(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            const json = {
                professors: ['Carlos Andrés', 'Harvey'],
                course: 'DOS'
            };
            return Promise.resolve(json)
                .then((response) => {
                console.log('Respuesta: ', response);
                res.status(200).json({
                    ok: true,
                    response
                });
            })
                .catch((error) => {
                res.status(400).json({ ok: false });
                throw new Error(`Error en getInfo(): ${error}`);
            });
        });
    }
}
exports.default = DAO;
