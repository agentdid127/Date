# Date

This project is to solve the world ending in 2038.
I was doing some googling about UNIX timestamps, and I found out that 32x systems won't really work after a certain date in 2038...

### What are some solutions to that?
Well, you could make everyone move to 64x systems (not fun, as it won't be backwards-compatible), or make everyone adopt a new system.
I wrote this package over a couple of hours to propose this as a possible solution. This allows for dates to be used as strings (which most solutions over a long-period of time supports).
You could use this as a new way of doing it.

An example of this new system would be using the UNIX epoch: `1970:01:01:00:00:00:000`

To break it down, every number is a part of a date,
1. Year
2. Month
3. Day
4. Hour
5. Minute
6. Second
7. Millisecond

Feel free to browse this project to get an understanding of my date system
