import { CodegenConfig } from "@graphql-codegen/cli";
import { GraphQLKotlinCodegenConfig } from "@expediagroup/graphql-kotlin-codegen";

export default {
  schema: "shared/src/commonMain/graphql/schema.graphqls",
  generates: {
    "generated/GraphModels.kt": {
      plugins: [
        {
          "@expediagroup/graphql-kotlin-codegen": {
            namingConvention: "keep", // graphql-codegen config
            packageName: "love.bside.models.graphql", // graphql-kotlin-codegen config
          } satisfies GraphQLKotlinCodegenConfig,
        },
      ],
    },
  },
} satisfies CodegenConfig;