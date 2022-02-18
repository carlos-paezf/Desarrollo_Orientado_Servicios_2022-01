"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const cors_1 = __importDefault(require("cors"));
const morgan_1 = __importDefault(require("morgan"));
const colors_1 = __importDefault(require("colors"));
const Routes_1 = __importDefault(require("../routes/Routes"));
class Server {
    constructor() {
        this.config = () => {
            this._app.set('PORT', 8888);
            this._app.use((0, cors_1.default)());
            this._app.use((0, morgan_1.default)('dev'));
            this._app.use(express_1.default.json({ limit: '100mb' }));
            this._app.use(express_1.default.urlencoded({ extended: true }));
        };
        this.routes = () => {
            this._app.use('/api/activity', Routes_1.default);
        };
        this.listen = () => {
            this._app.listen(this._app.get('PORT'), () => {
                console.log('Servidor corriendo en local: ', colors_1.default.underline.green(`http://localhost:${this._app.get('PORT')} \n`));
                console.log(`   ${colors_1.default.italic.blue('- getUsers: ')} ${colors_1.default.italic(`http://localhost:${this._app.get('PORT')}/api/activity/get-users`)}`);
                console.log(`   ${colors_1.default.italic.blue('- getProducts: ')} ${colors_1.default.italic(`http://localhost:${this._app.get('PORT')}/api/activity/get-products`)}`);
                console.log(`   ${colors_1.default.italic.blue('- getServices: ')} ${colors_1.default.italic(`http://localhost:${this._app.get('PORT')}/api/activity/get-services`)}`);
                console.log('\n\n');
            });
        };
        this._app = (0, express_1.default)();
        this.config();
        this.routes();
    }
}
exports.default = Server;
