import {login} from "../support/login";

describe('Posts page spec', () => {
  beforeEach(() => {
    login();
    cy.visit('http://localhost:4200/posts/detail/1');
    cy.intercept(
      {
        method: 'GET',
        url: '/api/post/1',
      },
      {fixture: 'post'}
    ).as('postData');

    cy.get('.list-article > :nth-child(1)').click();

  });

  it('User can see a page post detail', () => {
    cy.url().should('include', '/posts/detail/1')
  });

  it('User can see detail information ', () => {
    cy.get('.title').should('exist');
    cy.get('.subtitle > :nth-child(1)').should('exist');
    cy.get('.subtitle > :nth-child(2)').should('exist');
    cy.get('.subtitle > :nth-child(3)').should('exist');
    cy.get('.description').should('exist');
  });

  it('InputArea exist', () => {

    cy.get('textarea[formControlName=message]').should('exist');
  });
})
