<h4>What is MVI?</h4>

<p>MVI stands for Model-View-Intent. MVI is one of the newest architecture patterns for Android, inspired by the unidirectional and cyclical nature of the Cycle.js framework.</p>

<p>MVI works in a very different way compared to its distant relatives, MVC, MVP or MVVM. The role of
each MVI components is as follows:

Model represents a state. Models in MVI should be immutable to ensure a unidirectional data flow
between them and the other layers in your architecture. Like in MVP, Interfaces in MVI represent
Views, which are then implemented in one or more Activities or Fragments. Intent represents an
intention or a desire to perform an action, either by the user or the app itself. For every action,
a View receives an Intent. The Presenter observes the Intent, and Models translate it into a new state.</p>

MVI added more feature to existing architecture pattern MVVM

