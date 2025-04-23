import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HealioComponent } from './healio.component';

describe('HealioComponent', () => {
  let component: HealioComponent;
  let fixture: ComponentFixture<HealioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HealioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HealioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
