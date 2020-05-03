import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BsLocaleService, BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { format } from 'ts-date/esm/locale/en';

@Component({
  selector: 'app-datepicker',
  templateUrl: './datepicker.component.html',
  styleUrls: ['./datepicker.component.css']
})
export class DatepickerComponent implements OnInit {

  @Input() dpPlaceholder: string;
  @Input() dpPlacement: string;
  @Input() dpFormControlName: string;
  @Output() dpBsValueChange = new EventEmitter<any>();

  private day = new Date();

  public bsConfig: Partial<BsDatepickerConfig>;

  constructor(private localeService: BsLocaleService) { }

  ngOnInit() {
    this.datepickerConfig();
  }

  public onBsValueChange(event: Date): void {
    const date = format(event, 'YYYY-MM-DD');
    this.dpBsValueChange.emit(date);
  }

  public datepickerConfig(): void {
    this.localeService.use('engb');
    this.bsConfig = Object.assign({},
      { containerClass: 'theme-default',
      showWeekNumbers: false,
      isAnimated: true,
      dateInputFormat: 'YYYY-MM-DD',
      minDate: this.day
     });
  }

}
