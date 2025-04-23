import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withInterceptors, withFetch } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms'; // ✅ FIXED

import { routes } from './app.routes';
import { AuthInterceptor } from './interceptors/auth.interceptor'; // Adjust import path

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideClientHydration(),
    provideHttpClient(
      withInterceptors([AuthInterceptor]), // Using function-based interceptor
      withFetch() // Optional: keep this if you're using fetch-based HTTP requests
    ),
    importProvidersFrom(ReactiveFormsModule) // ✅ FIXED: Import ReactiveFormsModule
  ]
};
