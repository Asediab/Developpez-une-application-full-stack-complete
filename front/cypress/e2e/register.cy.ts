describe('User Registration spec', () => {
  it('Register passes', () => {
    cy.intercept('POST', '/api/auth/register', {
      body: {
        message: 'User registered successfully!',
      },
    }).as('register');

    cy.visit('http://localhost:4200/register');

    cy.get('input[formControlName=firstName]').type('user');
    cy.get('input[formControlName=email]').type('user@user.com');
    cy.get('input[formControlName=password]').type(`${'1F4@sf5s6,18F4@sf5s6'}{enter}{enter}`);

    cy.url().should('include', '/posts');
  })
})
