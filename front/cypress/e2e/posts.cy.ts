import {login} from "../support/login";

describe('Posts page spec', () => {
  beforeEach(() => {
    login();
    cy.visit('http://localhost:4200/posts');
  });

  it('User can see Posts cards', () => {

    cy.get('button.mat-ripple').its('length').should('equal', 8);

  });

  it('Posts have a title and a description', () => {

    cy.get('.mat-mdc-card-title').should('exist');
    cy.get('.mat-mdc-card-content').should('exist');
    cy.get('.post-date').should('exist');
    cy.get('.mat-mdc-card-subtitle > :nth-child(2)').should('exist');
  });

  it('Button Créer un article exist', () => {
    cy.get('.button-create').should('exist');
  });

  it('User have access to Create new post page', () => {
    cy.get('.button-create').click();
    cy.url().should('include', '/posts/create')
  });

  it('User can see form for create a new post', () => {
    cy.get('.button-create').click();

    cy.get('mat-select[formControlName=subjectId]').should('exist');
    cy.get('input[formControlName=title]').should('exist');
    cy.get('textarea[formControlName=description]').should('exist');
    cy.get('.button-sub').should('exist');

  });
})
