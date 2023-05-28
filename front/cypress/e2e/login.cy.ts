import {login} from "../support/login";

describe('User Login spec and post visible', () => {
  beforeEach(() => {
    login();
  });

  it('Login successful and posts visible', () => {

    cy.visit('http://localhost:4200/posts');

    cy.url().should('include', '/posts')
  })
});
