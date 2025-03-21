"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.default = {
    schema: "shared/src/commonMain/graphql/schema.graphqls",
    generates: {
        "generated/GraphModels.kt": {
            plugins: [
                {
                    "@expediagroup/graphql-kotlin-codegen": {
                        namingConvention: "keep", // graphql-codegen config
                        packageName: "love.bside.models.graphql", // graphql-kotlin-codegen config
                    },
                },
            ],
        },
    },
};
