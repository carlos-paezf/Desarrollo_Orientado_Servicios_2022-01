'use strict';
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const pg_promise_1 = __importDefault(require("pg-promise"));
class ConnectionFunctions {
}
/**
 * Camelize the column names in the data. This is a function that will convert the column names to camelCase.
 * @param {any} data - The data to be transformed.
 */
ConnectionFunctions.camelizeColumns = (data) => {
    const tmp = data[0];
    for (const prop in tmp) {
        const camel = pg_promise_1.default.utils.camelize(prop);
        if (!(camel in tmp)) {
            for (let i = 0; i < data.length; i++) {
                const d = data[i];
                d[camel] = d[prop];
                delete d[prop];
            }
        }
    }
};
exports.default = ConnectionFunctions;
