import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

interface CalendarEvent {
  date: Date;
  label: string;
}

@Component({
  selector: 'app-mini-calendar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './mini-calendar.component.html',
  styleUrls: ['./mini-calendar.component.scss']
})
export class MiniCalendarComponent {
  @Input() events: CalendarEvent[] = [];
  @Output() dateSelected = new EventEmitter<CalendarEvent | Date>();

  today = new Date();
  currentMonth = this.today.getMonth();
  currentYear = this.today.getFullYear();

  get daysInMonth(): number {
    return new Date(this.currentYear, this.currentMonth + 1, 0).getDate();
  }

  get firstDayOfWeek(): number {
    return new Date(this.currentYear, this.currentMonth, 1).getDay();
  }

  get calendarMatrix(): (Date | null)[] {
    const days: (Date | null)[] = [];
    // Remplir les jours vides avant le 1er du mois
    for (let i = 0; i < this.firstDayOfWeek; i++) {
      days.push(null);
    }
    // Remplir les jours du mois
    for (let d = 1; d <= this.daysInMonth; d++) {
      days.push(new Date(this.currentYear, this.currentMonth, d));
    }
    return days;
  }

  isEventDay(day: Date): CalendarEvent | undefined {
    return this.events.find(ev =>
      ev.date.getDate() === day.getDate() &&
      ev.date.getMonth() === day.getMonth() &&
      ev.date.getFullYear() === day.getFullYear()
    );
  }

  prevMonth() {
    if (this.currentMonth === 0) {
      this.currentMonth = 11;
      this.currentYear--;
    } else {
      this.currentMonth--;
    }
  }

  nextMonth() {
    if (this.currentMonth === 11) {
      this.currentMonth = 0;
      this.currentYear++;
    } else {
      this.currentMonth++;
    }
  }

  selectDay(day: Date | null) {
    if (!day) return;
    const event = this.isEventDay(day);
    this.dateSelected.emit(event || day);
  }
}
