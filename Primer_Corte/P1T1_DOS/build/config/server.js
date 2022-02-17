"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const cors_1 = __importDefault(require("cors"));
const morgan_1 = __importDefault(require("morgan"));
const express_1 = __importDefault(require("express"));
const colors_1 = __importDefault(require("colors"));
const routes_1 = __importDefault(require("../routes/routes"));
class Server {
    constructor() {
        this._app = (0, express_1.default)();
        this.config();
        this.routes();
    }
    /**
     * It sets the port, adds cors, logs requests, and sets the body limit.
     */
    config() {
        this._app.set('PORT', 8088);
        this._app.use((0, cors_1.default)());
        this._app.use((0, morgan_1.default)('dev'));
        this._app.use(express_1.default.json({ limit: '100mb' }));
        this._app.use(express_1.default.urlencoded({ extended: true }));
    }
    /**
     * It registers the routes for the application.
     */
    routes() {
        this._app.use('/api/test', routes_1.default);
    }
    /**
     * It starts the server.
     */
    init() {
        this._app.listen(this._app.get('PORT'), () => {
            console.log(`Servidor corriendo en modo local en ${colors_1.default.green.underline(`http://localhost:${this._app.get('PORT')}`)} \n`);
            console.log(`       - ${colors_1.default.italic.blue('get-info')}: ${colors_1.default.underline(`http://localhost:${this._app.get('PORT')}/api/test/get-info`)} \n\n`);
        });
    }
}
exports.default = Server;
