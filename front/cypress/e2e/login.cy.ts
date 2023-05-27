describe('User Login spec and post visible', () => {
  beforeEach(() => {
    cy.visit('http://localhost:4200/login')

    cy.intercept('POST', '/api/auth/login', {
      body: {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5b2dhQHN0dWRpby5jb20iLCJpYXQiOjE2ODUyMDIzNzEsImV4cCI6MTY4NTI4ODc3MX0.6zMYhv5VYgkXNbdN6vXoSpSMcTmb2hab9tlBSXb739qGj-2wpgIg1Gse87gK2JYqU-3nnbtFfOawvl4MnKYqZg",
        "lastName": "Taller6",
        "mail": "yoga@studio.com"
      },
    })
    cy.get('input[formControlName=email]').type('yoga@studio.com');
    cy.get('input[formControlName=password]').type(`${'1F4@sf5s6,18F4@sf5s6'}{enter}{enter}`);
    cy.intercept(
      {
        method: 'GET',
        url: '/api/post',
      },
      {fixture: 'posts'}
    ).as('postsData');
  });

  it('Login successful and posts visible', () => {

    cy.intercept(
      {
        method: 'GET',
        url: '/api/post',
      },
      {fixture: 'posts'}
    ).as('post')

    cy.visit('http://localhost:4200/posts');

    cy.url().should('include', '/posts')
  })
});
