import {defineConfig} from 'cypress'
import * as registerCodeCoverageTasks from '@cypress/code-coverage/task';

export default defineConfig({
  fixturesFolder: "cypress/fixtures",
  video: false,

  // setupNodeEvents can be defined in either
  // the e2e or component configuration
  e2e: {
    experimentalRunAllSpecs: true,
    setupNodeEvents(on, config) {
      registerCodeCoverageTasks(on, config);
      require('@cypress/code-coverage/task')(on, config)
      // include any other plugin code...

      // It's IMPORTANT to return the config object
      // with any changed environment variables
      return config
    },
  },
})
