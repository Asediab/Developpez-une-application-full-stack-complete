import {login} from "../support/login";

describe('Subjects page spec', () => {
  beforeEach(() => {
    login();
    cy.get('[routerlink="subjects"]').click();
    cy.intercept(
      {
        method: 'GET',
        url: '/api/subject',
      },
      {fixture: 'subjects'}
    ).as('subjData');
  });

  it('Displaying subjects list', () => {

    cy.get('.mat-mdc-card').its('length').should('equal', 3);

  });

  it('Button S\'abonner exist', () => {

    cy.get('.button-create').its('length').should('equal', 3);

  });

  it('Subject have a title and a description', () => {

    cy.get('.mat-mdc-card-title').should('exist');
    cy.get('.mat-mdc-card-content').should('exist');

  });

})
