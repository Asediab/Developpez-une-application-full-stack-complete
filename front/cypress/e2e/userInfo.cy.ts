import {login} from "../support/login";

describe('User info spec', () => {
  beforeEach(() => {
    login();
  });

  it('Displaying account info', () => {

    cy.get('#button-user').click();
    cy.get('input[formControlName=email]').should('exist');
    cy.get('input[formControlName=firstName]').should('exist');
    cy.get('input[formControlName=password]').should('exist');
    cy.get('.button-logout > .mat-mdc-button-touch-target').should('exist');
  });

  it('Button logOut exist', () => {

    cy.get('#button-user').click();
    cy.get('.button-logout').should('exist');
  });

  it('Subscription list exist', () => {

    cy.get('#button-user').click();
    cy.get('mat-card').its('length').should('equal', 3);
  });

  it('Button unsubscribe  exist', () => {

    cy.get('#button-user').click();
    cy.get('.button-create').should('exist');
    cy.get('mat-card').its('length').should('equal', 3);
  });

  it('Logout', () => {

    cy.get('#button-user').click();
    cy.get('.button-logout').should('exist');
    cy.get('.button-logout').click();
    cy.url().should('include', '')
  });
})
