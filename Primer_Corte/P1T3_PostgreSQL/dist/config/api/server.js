'use strict';
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    Object.defineProperty(o, k2, { enumerable: true, get: function() { return m[k]; } });
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importStar(require("express"));
const cors_1 = __importDefault(require("cors"));
const morgan_1 = __importDefault(require("morgan"));
const colors_1 = require("colors");
const faculty_routes_1 = __importDefault(require("../../routes/faculty.routes"));
const professor_routes_1 = __importDefault(require("../../routes/professor.routes"));
class Server {
    constructor() {
        this._paths = {
            faculties: '/api/faculties',
            professors: '/api/professors',
        };
        /**
         * This is where we configure the express server.
         */
        this.config = () => {
            this._app.set('PORT', this._port);
            this._app.use((0, cors_1.default)());
            this._app.use((0, morgan_1.default)('dev'));
            this._app.use((0, express_1.json)({ limit: '100mb' }));
            this._app.use((0, express_1.urlencoded)({ extended: true }));
        };
        /**
         * This is where we define the routes that the server will respond to.
         */
        this.routes = () => {
            this._app.use(this._paths.faculties, faculty_routes_1.default);
            this._app.use(this._paths.professors, professor_routes_1.default);
        };
        /**
         * This is where we start the server.
         */
        this.init = () => {
            this._app.listen(this._port, () => {
                console.log((0, colors_1.green)(`Server running locally on ${(0, colors_1.italic)(`http://localhost:${this._port}`)}`));
                console.log(`     - faculties ${colors_1.italic.underline(`http://localhost:${this._port}${this._paths.faculties}`)}`);
                console.log(`     - professors ${colors_1.italic.underline(`http://localhost:${this._port}${this._paths.professors}`)}`);
                console.log(`     - professors - type and faculty ${colors_1.italic.underline(`http://localhost:${this._port}${this._paths.professors}/type-and-faculty`)}`);
            });
        };
        this._app = (0, express_1.default)();
        this._port = process.env.PORT || '8081';
        this.config();
        this.routes();
    }
}
exports.default = Server;
