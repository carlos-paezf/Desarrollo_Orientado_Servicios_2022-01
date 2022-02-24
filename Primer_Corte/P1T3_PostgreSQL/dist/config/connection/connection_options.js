'use strict';
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const connection_functions_1 = __importDefault(require("./connection_functions"));
class ConnectionOptions {
}
/**
 * This is the configuration for the pg-promise library.
 */
ConnectionOptions.pgOptions = {
    receive(data, result, error) {
        connection_functions_1.default.camelizeColumns(data);
    }
};
exports.default = ConnectionOptions;
