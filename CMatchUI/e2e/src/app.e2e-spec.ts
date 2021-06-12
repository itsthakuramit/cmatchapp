import { browser, by, logging } from 'protractor';
import { AppPage } from './app.po';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', async () => {
    await page.navigateTo();
  });


  it('should have a title', () => {
    expect(browser.getTitle()).toContain('CMatchApp');
  });


  it('should be redirected to /dashboard route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/dashboard');
    browser.element(by.id('register-btn')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });
});


it('should be able to register', () => {
  browser.element(by.id('firstName')).sendKeys('First');
  browser.element(by.id('lastName')).sendKeys('Last');
  browser.element(by.id('userId')).sendKeys('email');
  browser.element(by.id('password')).sendKeys('password');
  browser.element(by.id('confirmPassword')).sendKeys('password');
  browser.element(by.id('jumbotron.btn')).submit();
  expect(browser.getCurrentUrl()).toContain('/login');
});


it('should be able to login', () => {
  browser.element(by.id('form-user')).sendKeys('udhay');
  browser.element(by.id('form-pass')).sendKeys('udhay');
  browser.element(by.id('jumbotron.btn')).click();
  expect(browser.getCurrentUrl()).toContain('/dashboard');
});


it('should be able to logout', () => {
  browser.element(by.id('logout')).click();
  expect(browser.getCurrentUrl()).toContain('/login');
});


it('should be able to add to watchlist', () => {
  const text = browser.element(by.id('watchListBtn')).getText();
  if (text.toString() === 'Add To Watchlist') {
      browser.element(by.id('watchListBtn')).click();
  }
  expect(browser.element(by.id('watchListBtn')).getText()).toContain('Remove From Watchlist');
  browser.element(by.id('logout')).click();
});


it('should be redirected to /new-matches route', () => {
  browser.element(by.id('newMatches')).click();
  expect(browser.getCurrentUrl()).toContain('/new-matches');
});


it('should be redirected to /old-matches route', () => {
  browser.element(by.id('oldMatches')).click();
  expect(browser.getCurrentUrl()).toContain('/old-matches');
});


it('should be redirected to /Watchlist route', () => {
  browser.element(by.id('fav-button')).click();
  expect(browser.getCurrentUrl()).toContain('/favourites');
});


it('should be redirected to /Recommendation route', () => {
  browser.element(by.id('recommend-button')).click();
  expect(browser.getCurrentUrl()).toContain('/recommendation');
});







