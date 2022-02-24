'use strict';
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
Object.defineProperty(exports, "__esModule", { value: true });
const colors_1 = require("colors");
const pg_promise_1 = __importDefault(require("pg-promise"));
const connection_options_1 = __importDefault(require("./connection_options"));
const var_db_config_1 = __importDefault(require("../domains/var_db.config"));
class ConnectionDB {
    constructor() {
        this._pgp = (0, pg_promise_1.default)(connection_options_1.default.pgOptions);
        /**
         * A function that connects to the database.
         */
        this.connectPool = () => __awaiter(this, void 0, void 0, function* () {
            try {
                const connection = yield this.pool.connect();
                connection.done();
                console.log(colors_1.blue.italic(`> Conexión establecida con la base de datos ${var_db_config_1.default.database}\n`));
            }
            catch (error) {
                console.log((0, colors_1.red)('Error in ConnectionDB: '), error);
            }
        });
        this.pool = this._pgp(var_db_config_1.default);
        this.connectPool();
    }
}
const connectionDB = new ConnectionDB();
exports.default = connectionDB;
