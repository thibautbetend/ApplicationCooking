const path = require("path");

module.exports = {
    outputDir: path.resolve(__dirname, "../backend/src/main/resources"),
    devServer: {
        proxy: 'http://localhost:8081/'
    }
}